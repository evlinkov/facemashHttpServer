DROP TABLE if exists users;

CREATE TABLE users (
  user_id       INTEGER               NOT NULL,
  group_id      INTEGER               NOT NULL,
  elo_rating    DOUBLE                NOT NULL      DEFAULT 1500,
  PRIMARY KEY(user_id, group_id)
);
