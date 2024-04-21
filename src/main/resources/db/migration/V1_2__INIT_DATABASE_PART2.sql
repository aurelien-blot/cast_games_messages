CREATE TABLE player
(
    id                INT AUTO_INCREMENT PRIMARY KEY,
    source_player_id                INT NOT NULL ,
    creation_time     TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modification_time TIMESTAMP    NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    username          VARCHAR(255) NOT NULL
);

CREATE TABLE conversation
(
    id                INT AUTO_INCREMENT PRIMARY KEY,
    creation_time     TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modification_time TIMESTAMP NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE private_conversation
(
    id         INT PRIMARY KEY,
    player1_id INT NOT NULL,
    player2_id INT NOT NULL,
    CONSTRAINT fk_private_conversation FOREIGN KEY (id) REFERENCES conversation (id),
    FOREIGN KEY (player1_id) REFERENCES player (id),
    FOREIGN KEY (player2_id) REFERENCES player (id)
);

CREATE TABLE group_conversation
(
    id   INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT fk_group_conversation FOREIGN KEY (id) REFERENCES conversation (id)
);

CREATE TABLE conversation_player
(
    conversation_id INT NOT NULL,
    player_id  INT NOT NULL,
    PRIMARY KEY (conversation_id, player_id),
    FOREIGN KEY (conversation_id) REFERENCES group_conversation (id),
    FOREIGN KEY (player_id) REFERENCES player (id)
);

CREATE TABLE message
(
    id                INT AUTO_INCREMENT PRIMARY KEY,
    creation_time     TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modification_time TIMESTAMP NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    author_id         INT       NOT NULL,
    conversation_id   INT       NOT NULL,
    content           TEXT      NOT NULL,
    read_at           TIMESTAMP NULL     DEFAULT NULL,
    FOREIGN KEY (author_id) REFERENCES player (id),
    FOREIGN KEY (conversation_id) REFERENCES conversation (id)
);