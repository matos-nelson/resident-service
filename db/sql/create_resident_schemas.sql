CREATE TABLE IF NOT EXISTS resident (
  id bigint PRIMARY KEY AUTO_INCREMENT,
  property_id bigint NOT NULL,
  user_id varchar(255) NOT NULL,
  preferred_name varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  full_name varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  email varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  phone varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
  created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  KEY property_id_idx (property_id),
  UNIQUE KEY user_id_idx (user_id),
  INDEX email_idx (email)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS resident_vehicle (
  id bigint PRIMARY KEY AUTO_INCREMENT,
  resident_id bigint NOT NULL,
  make varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  model varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  year_made integer DEFAULT NULL,
  color varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  license_num varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  KEY resident_id_idx (resident_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
