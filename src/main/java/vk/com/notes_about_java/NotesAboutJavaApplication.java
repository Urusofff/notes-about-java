package vk.com.notes_about_java;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import vk.com.notes_about_java.config.AppConfig;
import vk.com.notes_about_java.reactor_mono_block_deadlock.ReactorMonoBlockDeadlock;

public class NotesAboutJavaApplication {

    public static void main(final String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AppConfig.class);
        applicationContext.refresh();

        ReactorMonoBlockDeadlock reactorMonoBlockDeadlock = applicationContext.getBean(ReactorMonoBlockDeadlock.class);
        reactorMonoBlockDeadlock.groupClientsInFlux();
    }

}
