-- resident info 1
insert into resident(id, manager_id, property_id, user_id, full_name, email, phone) values (100, 'auth_user', 1, 'auth_user', 'First Resident', 'firstresident@email.com', '1234445555');
insert into resident_vehicle(id, resident_id, make, model, year_made, color, license_num) values (200, 100, 'Nissan', 'Rogue', 2000, 'Blue', 'AAA-123');

-- resident info 2
insert into resident(id, manager_id, property_id, user_id, full_name, email, phone) values (300, 'update_user', 1909, 'update_user', 'Update Resident', 'updateresidenttest@email.com', '1231234444');