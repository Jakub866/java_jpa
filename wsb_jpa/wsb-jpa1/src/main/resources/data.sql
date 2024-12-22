INSERT INTO address (id, address_line1, address_line2, city, postal_code)
VALUES
    (1, 'xx', 'yy', 'city', '62-030'),
    (2, 'Wojska Polskiego', '10/2', 'Wroclaw', '53-231'),
    (3, 'Kromera', '16/1', 'Wroclaw', '42-231');

insert into doctor (first_name, last_name, telephone_number, email, doctor_Number, specialization, address_id)
values ('Adam', 'Kowalski', 266123456, 'adam-kowalski@vp.ru','6823','OCULIST', 1);

insert into patient (first_name, last_name, telephone_number, email, patient_Number,date_of_birth, address_id, gender)
values ('Mariusz', 'Sylwestrzak', 123456978, 's.mariusz@onet.pl','1122','1991-04-12', 2, 'MALE'),
    ('Maryla','Adamska',999000333,'m.adamska@onet.pl','0912','1989-11-22', 3,'FEMALE');

insert into visit (description,time)
values ('Wizyta odnosnie depresji', '2024-12-20 14:30:00'),
       ('Wizyta odnosnie bolu glowy', '2024-12-19 15:40:00');

insert into medicaltreatment (description, type, visit_id)
values ('Ketamina 2mg/h','USG', 1),
       ('Xanax 10mg','RTG', 2);

insert into doctor_visits (doctor_entity_id,visits_id)
values (1,1), (1,2);

insert into patient_visits (patient_entity_id,visits_id)
values (1,1), (2,2);