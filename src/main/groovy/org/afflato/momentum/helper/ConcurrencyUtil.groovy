package org.afflato.momentum.helper

import groovy.util.logging.Slf4j
import org.afflato.momentum.model.MomentumDto
import org.afflato.momentum.service.TickerService
import org.afflato.momentum.utils.MappingHelper
import org.springframework.stereotype.Component

import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutionException
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.stream.Collectors


@Component
@Slf4j
class ConcurrencyUtil {
    MomentumDto getMomentumDto(List<String> added, List<String> removed, List<String> common, TickerService tickerService) {
        MomentumDto momentum = MomentumDto.builder().build()

        ExecutorService threadPool = Executors.newFixedThreadPool(3)
        CompletableFuture<List<MomentumDto.Security>> f1 = CompletableFuture.supplyAsync(() -> getSecurity(added, tickerService))
        CompletableFuture<List<MomentumDto.Security>> f2 = CompletableFuture.supplyAsync(() -> getSecurity(removed, tickerService))
        CompletableFuture<List<MomentumDto.Security>> f3 = CompletableFuture.supplyAsync(() -> getSecurity(common, tickerService))

        try {
            long timer = System.currentTimeMillis()
            List<MomentumDto.Security> s1 = f1.get()
            log.info("Time taken for added: "+(System.currentTimeMillis() - timer))
            List<MomentumDto.Security> s2 = f2.get()
            log.info("Time taken for removed: "+(System.currentTimeMillis() - timer))
            List<MomentumDto.Security> s3 = f3.get()
            log.info("Time taken for common: "+(System.currentTimeMillis() - timer))
            momentum = MomentumDto.builder()
                    .incoming(s1)
                    .outgoing(s2)
                    .common(s3).build()
        } catch (InterruptedException | ExecutionException e) {
            log.error(e.getMessage(), e)
        } finally {
            threadPool.shutdown()
        }

        return momentum
    }



    private static List<MomentumDto.Security> getSecurity(List<String> symbols, TickerService tickerService) {
        return symbols.parallelStream().map(e -> MomentumDto.Security.builder()
                .symbol(e)
                .subCategory(MappingHelper.getSubCategory(e))
                .prevClose(tickerService.getQuote(e).prevClose)
                .fiftyTwoWeekHigh(tickerService.getQuote(e).fiftyTwoWeekHigh)
                .fiftyTwoWeekLow(tickerService.getQuote(e).fiftyTwoWeekLow)
                .build()
        ).collect(Collectors.toList());
    }
}
