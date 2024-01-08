package com.centit.workorder;

import com.alibaba.fastjson2.JSON;
import com.centit.framework.common.SysParametersUtils;
import com.centit.search.service.ESServerConfig;
import com.centit.search.service.Impl.ESIndexer;
import com.centit.search.service.Impl.ESSearcher;
import com.centit.search.service.IndexerSearcherFactory;
import com.centit.support.algorithm.CollectionsOpt;
import com.centit.support.algorithm.UuidOpt;
import com.centit.workorder.po.HelpDoc;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Map;

/**
 * Created by zou_wy on 2017/8/2.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration
public class HelpDocTest {

    public static void main(String[] args) throws Exception {

        ESServerConfig esServerConfig = IndexerSearcherFactory.loadESServerConfigFormProperties(
            SysParametersUtils.loadProperties() );
        ESIndexer docIndexer = IndexerSearcherFactory.obtainIndexer(esServerConfig, HelpDoc.class);
        HelpDoc doc = new HelpDoc();
        doc.setOsId("zp_Qn5R5ROSo4sf-eovoWA");
        doc.setDocId(UuidOpt.getUuidAsString22());
        doc.setDocLevel(3);
        doc.setDocTitle("测试写入ES");
        doc.setDocFile("关于框架组件的帮组！");
        docIndexer.saveNewDocument(doc);

        System.out.println("done");
    }

    public static void main2(String[] args) throws Exception {

        ESServerConfig esServerConfig = IndexerSearcherFactory.loadESServerConfigFormProperties(
                SysParametersUtils.loadProperties() );
        ESSearcher fetchSearcher = IndexerSearcherFactory.obtainSearcher(esServerConfig, HelpDoc.class);

        Pair<Long, List<Map<String, Object>>> res = fetchSearcher.search(
            CollectionsOpt.createHashMap("osId", "zp_Qn5R5ROSo4sf-eovoWA"),
            "组件", 1, 20);

        System.out.println(JSON.toJSONString(res));
    }
}
