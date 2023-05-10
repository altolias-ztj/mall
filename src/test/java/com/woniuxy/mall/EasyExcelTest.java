package com.woniuxy.mall;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.woniuxy.mall.pojo.GoodsData;
import org.junit.jupiter.api.Test;

public class EasyExcelTest {
    @Test
    public void testRead() {
        String fileName = "D://商品信息.xlsx";
        EasyExcel.read(fileName, GoodsData.class, new AnalysisEventListener<GoodsData>() {
            @Override
            public void invoke(GoodsData data, AnalysisContext context) {
                System.out.println(data);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                System.out.println("完成");
            }
        }).sheet().doRead();

    }
}
