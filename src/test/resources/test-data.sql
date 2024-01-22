-- primary resident info 1
insert into resident(id, full_name, email, phone) values (100, 'First Resident', 'firstresident@email.com', '1234445555');
insert into primary_resident(id, manager_id, property_id, user_id) values (100, 'auth_user', 1, 'auth_user');
insert into resident_vehicle(id, resident_id, make, model, year_made, color, license_num) values (200, 100, 'Nissan', 'Rogue', 2000, 'Blue', 'AAA-123');

insert into resident(id, preferred_name, full_name, email, phone) values (200, 'Preferred Name', 'Co Resident', 'coresident@email.com', '9891231234');
insert into co_resident(id, resident_id) values (200, 100);

-- primary resident info 2
insert into resident(id, full_name, email, phone) values (300, 'Update Resident', 'updateresidenttest@email.com', '1231234444');
insert into primary_resident(id, manager_id, property_id, user_id) values (300, 'update_user', 1909, 'update_user');