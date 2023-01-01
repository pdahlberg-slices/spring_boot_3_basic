-- "user" is not a valid table name... hence user_tbl
CREATE TABLE user_tbl(
   id uuid NOT NULL PRIMARY KEY,
   username TEXT NOT NULL,
   password TEXT NOT NULL,
   state VARCHAR(16) NOT NULL,
   modified_at TIMESTAMPTZ NULL,
   created_at TIMESTAMPTZ NULL
);

