CREATE TABLE data_provider_info
(
  id                    BIGINT         NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name                  VARCHAR(255)   NOT NULL,
  jdbc_url              VARCHAR(2048)  NOT NULL,
  query_to_retrieve     VARCHAR(65536) NOT NULL,
  sync_interval_seconds BIGINT         NOT NULL
);
