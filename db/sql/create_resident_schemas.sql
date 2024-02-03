CREATE TABLE IF NOT EXISTS resident (
  id bigint PRIMARY KEY AUTO_INCREMENT,
  manager_id varchar(255) NOT NULL,
  property_id bigint NOT NULL,
  user_id varchar(255) NOT NULL,
  tenant_id bigint NOT NULL,
  created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  KEY manager_id_idx (manager_id),
  KEY property_id_idx (property_id),
  KEY tenant_id_idx (tenant_id),
  UNIQUE KEY user_id_idx (user_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
