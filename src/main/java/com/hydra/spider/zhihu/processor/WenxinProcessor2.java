package com.hydra.spider.zhihu.processor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * @author : Hydra
 * @date: 2023/3/20 13:17
 * @version: 1.0
 */
public class WenxinProcessor2 implements PageProcessor {
    private Site site = Site.me()
            .setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
        String contentPath= "//div[@class='RichContent-inner']/tidyText()";
        List<String> answerList = page.getHtml().xpath(contentPath).all();
        for (int i = 0; i < answerList.size(); i++) {
            System.out.println("第"+(i+1)+"条回答:");
            System.out.println(answerList.get(i)+"\n=======");
        }
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new WenxinProcessor2())
                .addUrl("https://www.zhihu.com/question/589929380")
                .thread(2)
                .run();
    }
}
