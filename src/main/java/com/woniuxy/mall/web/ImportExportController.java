package com.woniuxy.mall.web;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.ListUtils;
import com.sun.deploy.net.URLEncoder;
import com.woniuxy.mall.commons.GoodsMapper;
import com.woniuxy.mall.entity.Goods;
import com.woniuxy.mall.pojo.GoodsData;
import com.woniuxy.mall.service.GoodsService;
import com.woniuxy.mall.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/impexp")
public class ImportExportController {

    @Resource
    private GoodsService goodsService;

    @GetMapping("import")
    public ResponseResult<Void> importGoods(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), GoodsData.class, new AnalysisEventListener<GoodsData>() {
                    private static final int BATCH_COUNT = 5;
                    private List<Goods> cachedDataLiat = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

                    @Override
                    public void invoke(GoodsData goodsData, AnalysisContext analysisContext) {

                    }

                    @Override
                    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

                    }
                }
        );
        return ResponseResult.ok();
    }

    @Autowired
    private GoodsMapper goodsMapper;

    @GetMapping("download")
    public void download(HttpServletResponse response) throws IOException {
        List<Goods> goods = goodsService.list();
        List<GoodsData> goodsData = goodsMapper.map(goods);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("商品信息", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), GoodsData.class).sheet("模板").doWrite(goodsData);
    }
}
