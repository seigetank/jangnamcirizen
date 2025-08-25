-- Users
CREATE TABLE IF NOT EXISTS users (
  id BIGSERIAL PRIMARY KEY,
  email VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  name VARCHAR(100) NOT NULL,
  role VARCHAR(100) NOT NULL DEFAULT 'ROLE_USER',
  created_at TIMESTAMPTZ DEFAULT now()
);

-- Media (이미지/첨부 메타: R2 연동 대비)
CREATE TABLE IF NOT EXISTS media (
  id BIGSERIAL PRIMARY KEY,
  owner_id BIGINT REFERENCES users(id) ON DELETE SET NULL,
  key VARCHAR(512) NOT NULL,    -- R2 object key
  url TEXT NOT NULL,            -- public/presigned URL
  content_type VARCHAR(100),
  size_bytes BIGINT,
  created_at TIMESTAMPTZ DEFAULT now()
);

CREATE INDEX IF NOT EXISTS idx_users_email ON users(email);
CREATE INDEX IF NOT EXISTS idx_media_owner ON media(owner_id);
