DROP TABLE if exists votes;
CREATE TABLE votes (
  id              BIGINT AUTO_INCREMENT,
  user_id         INTEGER               NOT NULL,
  left_user_id    INTEGER               NOT NULL,
  right_user_id   INTEGER               NOT NULL,
  vote            BOOLEAN,
  PRIMARY KEY (id)
);
CREATE INDEX votes_user_id_index ON votes(user_id);