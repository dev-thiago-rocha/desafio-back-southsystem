CREATE TABLE `voting_session`
(
    `id`          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `topic_id`    BIGINT UNSIGNED NOT NULL,
    `created_at`  DATETIME        NOT NULL,
    `finish_date` DATETIME        NOT NULL,
    `status`      VARCHAR(100)    NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT fk_voting_session_topic FOREIGN KEY (topic_id) REFERENCES topic (id)
)