drop table if exists computer;

create table computer(
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         nazwa VARCHAR(255) NOT NULL,
                         data_ksiegowania DATE,
                         koszt_USD float,
                         koszt_PLN float
);