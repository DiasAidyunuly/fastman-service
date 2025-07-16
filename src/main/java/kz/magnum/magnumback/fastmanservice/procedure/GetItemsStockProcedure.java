package kz.magnum.magnumback.fastmanservice.procedure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kz.magnum.magnumback.fastmanservice.model.pdt.GetItemsStockModel;
import kz.magnum.magnumback.fastmanservice.model.pdt.GoldItemModel;
import kz.magnum.magnumback.fastmanservice.model.pdt.GoldItemParamModel;
import kz.magnum.magnumback.fastmanservice.util.DbMapper;
import oracle.jdbc.OracleTypes;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.util.LinkedCaseInsensitiveMap;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GetItemsStockProcedure extends StoredProcedure {
    public GetItemsStockProcedure(DataSource dataSource) {
        super(dataSource, "CEN510MGN.ZPKG_TSD_STFM.pgetItemsBalance");
        declareParameter(new SqlParameter("siteCode", OracleTypes.INTEGER));
        declareParameter(new SqlParameter("pitemsList", OracleTypes.CLOB));
        declareParameter(new SqlOutParameter("itemsList", OracleTypes.REF_CURSOR));
        declareParameter(new SqlOutParameter("PRESULT", OracleTypes.VARCHAR));
        setFunction(false);
        compile();
    }

    public GetItemsStockModel execute(Short siteCode, List<GoldItemParamModel> items) {
        List<GoldItemModel> itemsFromCursor = new ArrayList<>();
        String itemsJson = createJson(items);
        Map<String, Object> map = super.execute(siteCode, itemsJson);
        List<LinkedCaseInsensitiveMap<Object>> cursor = (List<LinkedCaseInsensitiveMap<Object>>) map.get("itemsList");
        if (cursor != null) {
            for (LinkedCaseInsensitiveMap<Object> row : cursor) {
                GoldItemModel goldItemModel = GoldItemModel
                    .builder()
                    .itemCode(DbMapper.objToStr(row, "ITEM_CODE"))
                    .itemBalance(DbMapper.objToDouble(row, "ITEM_BALANCE"))
                    .build();
                itemsFromCursor.add(goldItemModel);
            }
        }
        return GetItemsStockModel.builder()
            .siteCode(siteCode)
            .items(itemsFromCursor)
            .build();
    }

    private String createJson(List<GoldItemParamModel> items) {
        List<Map<String, String>> itemList = items.stream()
            .map(item -> Map.of("itemCode", item.getItemCode()))
            .collect(Collectors.toList());

        Map<String, Object> wrapper = new HashMap<>();
        wrapper.put("itemsList", itemList);

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(wrapper);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Ошибка сериализации JSON", e);
        }
    }
}