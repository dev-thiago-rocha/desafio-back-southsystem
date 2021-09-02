CREATE TABLE `vote`
(
    `id`                BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `cpf`               VARCHAR(11)     NOT NULL,
    `decision`          VARCHAR(100)    NOT NULL,
    `voting_session_id` BIGINT UNSIGNED NOT NULL,
    `created_at`        DATETIME        NOT NULL,

    PRIMARY KEY (`id`),
    CONSTRAINT fk_vote_voting_session FOREIGN KEY (voting_session_id) REFERENCES voting_session (id)
)