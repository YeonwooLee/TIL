DROP DATABASE IF EXISTS ssafy_board;
CREATE DATABASE ssafy_board DEFAULT CHARACTER SET utf8mb4;

USE ssafy_board;

CREATE TABLE board (
	id INT AUTO_INCREMENT,
    writer VARCHAR(20) NOT NULL,
    title VARCHAR(50) NOT NULL,
    content TEXT,
    view_cnt INT DEFAULT 0,
    reg_date TIMESTAMP DEFAULT now(),
    PRIMARY KEY(id)
);

INSERT INTO board(title, writer, content) 
VALUES ("BackEnd 너두 마슷허","양씨","너도 할 수 있어"),
	   ("누르지마시오", "따봉맨", "아무내용없음"),
       ("대답잘하는 법", "송소연", "채팅 잘치면됨 네(필쑤)");


CREATE TABLE `curriculum` (
	`code` INT NOT NULL PRIMARY KEY,
    `name` VARCHAR(40) NOT NULL
)ENGINE=InnoDB;


CREATE TABLE IF NOT EXISTS `users` (
  `id` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `name` varchar(40) NOT NULL,
  `curriculum_code` INT NOT NULL,
  CONSTRAINT `curriculum_fk` FOREIGN KEY (`curriculum_code`) REFERENCES `curriculum`(`code`),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARACTER SET = utf8mb4;

INSERT INTO `curriculum` 
VALUES (100, 'Python'), (200, 'Java'), (300, 'Embedded'), (400, 'Mobile');

INSERT INTO users
VALUES ("ssafy", "1234", "김싸피", 200);


SELECT * FROM board;