package com.just.sapi.blog.service;

import com.just.sapi.blog.dto.BlogSearchRankDTO;
import com.just.sapi.blog.entity.BlogSearchLogEntity;
import com.just.sapi.blog.repository.BlogSearchLogRepository;
import com.just.sapi.blog.repository.BlogSearchRankRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional
class BlogServiceTest {
    @Autowired
    BlogService blogService;
    @Autowired
    BlogSearchLogRepository blogSearchLogRepository;
    @Autowired
    BlogSearchRankRepository blogSearchRankRepository;

    @Test
    void 키워드_검색_동시성_테스트() throws InterruptedException {
        int numberOfThreads = 1000;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(() -> {
                try {
                    blogService.setBlogSearchLog("테스트");
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        List<BlogSearchLogEntity> list = blogSearchLogRepository.findAllByKeywordEquals("테스트");
        assertThat(list.size()).isEqualTo(numberOfThreads);
    }


    @Test
    void 검색로그_집계_스케줄러_테스트() {
        String[] keywords = new String[] {"키워드1", "키워드2", "키워드3", "키워드4", "키워드5", "키워드6", "키워드7", "키워드8", "키워드9", "키워드10" };
        int loopCount = 1000;
        for (int i = 0; i < loopCount; i++) {
            blogSearchLogRepository.saveAndFlush(
                    BlogSearchLogEntity.builder()
                            .keyword(keywords[new Random().nextInt(10)])
                            .build()
            );

        }
        List<BlogSearchLogEntity> list = blogSearchLogRepository.findBlogSearchLogEntitiesByKeywordIn(Arrays.stream(keywords).toList());
        blogService.scheduleAggregateBlogSearchLog();
        List<BlogSearchRankDTO> blogSearchRankDTOList = blogService.getBlogSearchRankList();
        assertThat(blogSearchRankDTOList.stream().mapToLong(BlogSearchRankDTO::getCount).sum()).isEqualTo(loopCount);
    }


}