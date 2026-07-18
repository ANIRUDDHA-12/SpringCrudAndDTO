create table book(
    id Int AUTO_INCREMENT PRIMARY KEY,
    title varchar(255) not null unique ,
    isbn varchar(255) not null unique,
    is_available BOOLEAN NOT NULL DEFAULT TRUE
);

create table member(
    id Int auto_increment PRIMARY KEY,
    name varchar(255) not null unique,
    email varchar(255) not null unique,
    membership_type varchar(255) not null
);

create table book_loan(
    id Int auto_increment PRIMARY KEY,
    borrow_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    return_date TIMESTAMP,
    member_id INT NOT NULL,
    book_id INT NOT NULL,
    CONSTRAINT fk_book_loan_member FOREIGN KEY (member_id) REFERENCES member(id) ON DELETE CASCADE,
    CONSTRAINT fk_book_loan_book FOREIGN KEY (book_id) REFERENCES book(id) ON DELETE CASCADE
);

--CREATE INDEX idx_book_loan_member ON book_loan(member_id);
--CREATE INDEX idx_book_loan_book ON book_loan(book_id);

INSERT INTO member (name, email, membership_type) VALUES ('Alice', 'alice@email.com', 'PREMIUM');
INSERT INTO book (title, isbn, is_available) VALUES ('Spring Boot in Action', '123456789', TRUE);