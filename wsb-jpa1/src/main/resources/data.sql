insert into address (id, address_line1, address_line2, city, postal_code)
values 
    (1, 'ul. Główna', 'Mieszkanie 1', 'Warszawa', '00-001'),
    (2, 'ul. Szeroka', 'Lok. 12', 'Kraków', '30-001');

insert into doctor (id, first_name, last_name, telephone_number, email, doctor_number, specialization, address_id)
values 
    (1, 'Jan', 'Kowalski', '123456789', 'jan.kowalski@example.com', 'D123', 'KARDIOLOGIA', 1),
    (2, 'Anna', 'Nowak', '987654321', 'anna.nowak@example.com', 'D124', 'DERMATOLOGIA', 2);

insert into patient (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, is_insured, address_id)
values
    (1, 'Marek', 'Wiśniewski', '555123456', 'marek.wisniewski@example.com', 'P001', '1985-05-15', true, 1),
    (2, 'Katarzyna', 'Zielińska', '555987654', 'katarzyna.zielinska@example.com', 'P002', '1990-08-20', false, 2),
    (3, 'Piotr', 'Kowalski', '555987123', 'piotr.kowalski@example.com', 'P003', '1980-01-01', true, 2);

insert into medical_treatment (id, description, type)
values 
    (1, 'Badanie ogólne', 'OGÓLNE'),
    (2, 'Leczenie skóry', 'DERMATOLOGIA'),
    (3, 'Operacja serca', 'KARDIOLOGIA');

insert into visit (id, description, time, patient_id, doctor_id)
values 
    (1, 'Kontrola rutynowa', '2025-01-08T10:00:00', 1, 1),
    (2, 'Konsultacja dermatologiczna', '2025-01-08T14:00:00', 2, 2),
    (3, 'Badanie okresowe', '2025-01-09T10:00:00', 1, 1),
    (4, 'Leczenie dermatologiczne', '2025-01-10T10:00:00', 2, 2),
    (5, 'Operacja chirurgiczna', '2025-01-11T12:00:00', 3, 1);

insert into visit_treatment (visit_id, treatment_id)
values 
    (1, 1),
    (2, 2),
    (3, 1),
    (4, 2),
    (5, 3);
