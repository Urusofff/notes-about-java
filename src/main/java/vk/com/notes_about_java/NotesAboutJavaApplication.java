package vk.com.notes_about_java;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import vk.com.notes_about_java.config.AppConfig;
import vk.com.notes_about_java.reactor_mono_block_deadlock.ReactorMonoBlockDeadlock;

/**
 * @author Maksim Urusov
 *
 * @see <a href="https://vk.com/notes_about_java"> VK.com</a>
 * @see <a href="https://t.me/notes_about_java"> Telegram</a>
 * @see <a href="https://boosty.to/urusoff"> Boosty</a>
 */

public class NotesAboutJavaApplication {

    public static void main(final String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AppConfig.class);
        applicationContext.refresh();

        ReactorMonoBlockDeadlock reactorMonoBlockDeadlock = applicationContext.getBean(ReactorMonoBlockDeadlock.class);
        reactorMonoBlockDeadlock.groupClientsInFlux();
    }

}
