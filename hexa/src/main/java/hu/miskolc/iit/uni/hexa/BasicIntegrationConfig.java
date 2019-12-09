package hu.miskolc.iit.uni.hexa;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.filters.AcceptOnceFileListFilter;
import org.springframework.integration.file.filters.CompositeFileListFilter;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;
import org.springframework.messaging.MessageChannel;

import java.io.File;
import java.util.Arrays;

@Configuration
@EnableIntegration
public class BasicIntegrationConfig {
    public static final String AuthorInputDirectory = "authors";
    public static final String FilePattern = "*.json";

    @Bean
    public MessageChannel authorFileNameChannel(){
        return new DirectChannel();
    }
    @Bean
    public MessageChannel authorChannel(){
        return new DirectChannel();
    }

    @Bean
    @InboundChannelAdapter(channel = "authorFileNameChannel", poller = @Poller(fixedDelay = "1000"))
    public MessageSource<File> fileReadingMessageSource(){
        FileReadingMessageSource sourceReader = new FileReadingMessageSource();
        sourceReader.setDirectory(new File(AuthorInputDirectory));
        sourceReader.setFilter(
                new CompositeFileListFilter<>(
                        Arrays.asList(
                                new SimplePatternFileListFilter(FilePattern),
                                new AcceptOnceFileListFilter<>(10))
                )
        );

        return sourceReader;
    }

    @Bean
    public MessageChannel bookFileNameChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel bookChannel() {
        return new DirectChannel();
    }
}
