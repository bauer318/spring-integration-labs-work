package ru.rsreu.kibamba.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.BridgeFrom;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import java.io.File;

@Configuration
@EnableIntegration
public class BasicIntegrationConfig {
    public String INPUT_DIR = "the_source_dir";
    public String OUTPUT_DIR = "the_dest_dir1";
    public String OUTPUT_DIR2 = "the_dest_dir2";
    public String OUTPUT_DIR3 = "the_dest_dir3";
    public String FILE_PATTERN = "*.jpg";

    @Bean
    @BridgeFrom(value = "pubSubFileChannel")
    public MessageChannel fileChannel1() {
        return new DirectChannel();
    }
    @Bean
    @BridgeFrom(value = "pubSubFileChannel")
    public MessageChannel fileChannel2(){
        return new DirectChannel();
    }
    @Bean
    @BridgeFrom(value = "pubSubFileChannel")
    public MessageChannel fileChannel3(){
        return new DirectChannel();
    }

    @Bean
    @InboundChannelAdapter(value = "pubSubFileChannel", poller = @Poller(fixedDelay = "1000"))
    public MessageSource<File> fileReadingMessageSource() {
        FileReadingMessageSource sourceReader= new FileReadingMessageSource();
        sourceReader.setDirectory(new File(INPUT_DIR));
        sourceReader.setFilter(new SimplePatternFileListFilter(FILE_PATTERN));
        return sourceReader;
    }

    @Bean
    @ServiceActivator(inputChannel= "fileChannel1")
    public MessageHandler fileWritingMessageHandler1() {
        FileWritingMessageHandler handler = new FileWritingMessageHandler(new File(OUTPUT_DIR));
        handler.setFileExistsMode(FileExistsMode.REPLACE);
        handler.setExpectReply(false);
        return handler;
    }

    @Bean
    @ServiceActivator(inputChannel= "fileChannel2")
    public MessageHandler fileWritingMessageHandler2() {
        FileWritingMessageHandler handler = new FileWritingMessageHandler(new File(OUTPUT_DIR2));
        handler.setFileExistsMode(FileExistsMode.REPLACE);
        handler.setExpectReply(false);
        return handler;
    }

    @Bean
    @ServiceActivator(inputChannel= "fileChannel3")
    public MessageHandler fileWritingMessageHandler3() {
        FileWritingMessageHandler handler = new FileWritingMessageHandler(new File(OUTPUT_DIR3));
        handler.setFileExistsMode(FileExistsMode.REPLACE);
        handler.setExpectReply(false);
        return handler;
    }
}
