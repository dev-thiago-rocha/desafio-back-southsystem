CREATE TABLE `topic`
(
    `id`           BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `title`        VARCHAR(255)    NOT NULL,
    `created_at`   DATETIME        NOT NULL,
    PRIMARY KEY (`id`)
)