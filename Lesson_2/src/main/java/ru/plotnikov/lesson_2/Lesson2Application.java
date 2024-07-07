package ru.plotnikov.lesson_2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Lesson2Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context =SpringApplication.run(Lesson2Application.class, args);

		Board board = context.getBean(Board.class);

		for (int i = 0; i < 10; i++) {
			System.out.println(board.newTicket());
		}


	}

}
