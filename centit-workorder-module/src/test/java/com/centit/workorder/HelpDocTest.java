package com.centit.workorder;

import com.centit.support.network.HttpExecutor;
import com.centit.support.network.HttpExecutorContext;
import com.centit.workorder.po.HelpDoc;
import org.apache.http.impl.client.CloseableHttpClient;

/**
 * Created by zou_wy on 2017/8/2.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration
public class HelpDocTest {

    public static void main(String[] args) throws Exception {

        HelpDoc helpDoc = new HelpDoc();
        helpDoc.setCatalogId("123");
        helpDoc.setDocTitle("标题");
        helpDoc.setDocFile("管理员");
        helpDoc.setOsId("FILE_SVR");
        helpDoc.setOptId("search");
        helpDoc.setOptMethod("search");

        try(CloseableHttpClient httpClient = HttpExecutor.createHttpClient()) {
            String jsonStr = HttpExecutor.formPost(
                    HttpExecutorContext.create(httpClient),
                    "http://localhost:8384/workorder/service/os/FILE_SVR/documents", helpDoc);
            System.out.println(jsonStr);

            jsonStr = HttpExecutor.jsonPost(HttpExecutorContext.create(httpClient),
                    "http://localhost:8384/workorder/service/os/FILE_SVR/documents", helpDoc);
            System.out.println(jsonStr);
        }
    }
}
