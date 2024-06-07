
INSERT INTO authors (id, firstname, lastname, dob, created_date, created_by, updated_date, updated_by)
VALUES
(UUID(), 'John', 'Smith', '1970-01-01', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system'),
(UUID(), 'Emma', 'Johnson', '1971-02-01', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system'),
(UUID(), 'Liam', 'Brown', '1972-03-01', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system'),
(UUID(), 'Olivia', 'Garcia', '1973-04-01', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system'),
(UUID(), 'Noah', 'Martinez', '1974-05-01', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system'),
(UUID(), 'Ava', 'Rodriguez', '1975-06-01', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system'),
(UUID(), 'William', 'Lee', '1976-07-01', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system'),
(UUID(), 'Sophia', 'Hernandez', '1977-08-01', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system'),
(UUID(), 'James', 'Wong', '1978-09-01', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system'),
(UUID(), 'Mia', 'Kim', '1979-10-01', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system'),
(UUID(), 'Lucas', 'Chen', '1980-11-01', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system'),
(UUID(), 'Isabella', 'Tanaka', '1981-12-01', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system'),
(UUID(), 'Benjamin', 'Suzuki', '1982-01-01', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system'),
(UUID(), 'Charlotte', 'Nakamura', '1983-02-01', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system'),
(UUID(), 'Alexander', 'Park', '1984-03-01', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system'),
(UUID(), 'Amelia', 'Choi', '1985-04-01', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system'),
(UUID(), 'Ethan', 'Nguyen', '1986-05-01', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system');


INSERT INTO publishers (id, name, address, phone_number, email, created_date, created_by, updated_date, updated_by)
VALUES
(UUID(), 'Penguin Random House', '1745 Broadway, New York, NY 10019', '212-782-9000', 'info@penguinrandomhouse.com', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system'),
(UUID(), 'HarperCollins', '195 Broadway, New York, NY 10007', '212-207-7000', 'info@harpercollins.com', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system'),
(UUID(), 'Simon & Schuster', '1230 Avenue of the Americas, New York, NY 10020', '212-698-7000', 'info@simonandschuster.com', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system'),
(UUID(), 'Hachette Book Group', '1290 Avenue of the Americas, New York, NY 10104', '212-364-1200', 'info@hachettebookgroup.com', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system'),
(UUID(), 'Macmillan Publishers', '120 Broadway, New York, NY 10271', '646-307-5151', 'info@macmillan.com', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system'),
(UUID(), 'Scholastic', '557 Broadway, New York, NY 10012', '212-343-6100', 'info@scholastic.com', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system'),
(UUID(), 'Bloomsbury', '50 Bedford Square, London, WC1B 3DP, UK', '+44 20 7631 5600', 'info@bloomsbury.com', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system'),
(UUID(), 'Houghton Mifflin Harcourt', '125 High Street, Boston, MA 02110', '617-351-5000', 'info@hmhco.com', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system'),
(UUID(), 'Pearson', '221 River Street, Hoboken, NJ 07030', '201-236-7000', 'info@pearson.com', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system'),
(UUID(), 'Wiley', '111 River Street, Hoboken, NJ 07030', '201-748-6000', 'info@wiley.com', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system'),
(UUID(), 'Springer', '233 Spring Street, New York, NY 10013', '212-460-1500', 'info@springer.com', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system'),
(UUID(), 'Oxford University Press', '198 Madison Avenue, New York, NY 10016', '212-726-6000', 'info@oup.com', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'system');

INSERT INTO books (id, isbn, title, genre, publication_year, copies_available, price, publisher_id, created_by, updated_by, created_date, updated_date)
VALUES
(UUID(), '978-3-16-148410-0', 'The Great Gatsby', 'Fiction', '1925-04-10', 10, 15.99, (SELECT id FROM publishers WHERE name = 'Penguin Random House'), 'system', 'system', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(UUID(), '978-0-06-112008-4', 'To Kill a Mockingbird', 'Fiction', '1960-07-11', 8, 10.99, (SELECT id FROM publishers WHERE name = 'HarperCollins'), 'system', 'system', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(UUID(), '978-0-7432-7356-5', '1984', 'Dystopian', '1949-06-08', 12, 12.99, (SELECT id FROM publishers WHERE name = 'Simon & Schuster'), 'system', 'system', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(UUID(), '978-0-452-28423-4', 'Brave New World', 'Dystopian', '1932-08-01', 15, 13.99, (SELECT id FROM publishers WHERE name = 'Hachette Book Group'), 'system', 'system', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(UUID(), '978-0-14-118776-1', 'Catch-22', 'Satire', '1961-11-10', 7, 14.99, (SELECT id FROM publishers WHERE name = 'Penguin Random House'), 'system', 'system', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(UUID(), '978-0-307-26369-8', 'Life of Pi', 'Adventure', '2001-09-11', 9, 16.99, (SELECT id FROM publishers WHERE name = 'Houghton Mifflin Harcourt'), 'system', 'system', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(UUID(), '978-0-7432-7356-6', 'The Catcher in the Rye', 'Fiction', '1951-07-16', 10, 11.99, (SELECT id FROM publishers WHERE name = 'Simon & Schuster'), 'system', 'system', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(UUID(), '978-1-5011-3695-9', 'The Road', 'Post-apocalyptic', '2006-09-26', 13, 17.99, (SELECT id FROM publishers WHERE name = 'HarperCollins'), 'system', 'system', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(UUID(), '978-0-06-092987-9', 'One Hundred Years of Solitude', 'Magic realism', '1967-05-30', 8, 18.99, (SELECT id FROM publishers WHERE name = 'HarperCollins'), 'system', 'system', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(UUID(), '978-0-14-118280-3', 'Moby-Dick', 'Adventure', '1851-10-18', 6, 12.99, (SELECT id FROM publishers WHERE name = 'Penguin Random House'), 'system', 'system', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(UUID(), '978-0-14-144233-3', 'Pride and Prejudice', 'Romance', '1813-01-28', 5, 9.99, (SELECT id FROM publishers WHERE name = 'Penguin Random House'), 'system', 'system', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(UUID(), '978-0-375-50420-6', 'Beloved', 'Fiction', '1987-09-18', 7, 13.99, (SELECT id FROM publishers WHERE name = 'Hachette Book Group'), 'system', 'system', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(UUID(), '978-0-14-044793-4', 'The Brothers Karamazov', 'Philosophical', '1880-11-01', 4, 14.99, (SELECT id FROM publishers WHERE name = 'Penguin Random House'), 'system', 'system', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(UUID(), '978-0-19-953556-9', 'War and Peace', 'Historical', '1869-01-01', 3, 19.99, (SELECT id FROM publishers WHERE name = 'Oxford University Press'), 'system', 'system', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(UUID(), '978-0-14-118257-5', 'Crime and Punishment', 'Philosophical', '1866-01-01', 2, 10.99, (SELECT id FROM publishers WHERE name = 'Penguin Random House'), 'system', 'system', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


INSERT INTO book_authors (book_id, author_id)
VALUES
((SELECT id FROM books WHERE isbn = '978-3-16-148410-0'), (SELECT id FROM authors WHERE firstname = 'John' AND lastname = 'Smith')),
((SELECT id FROM books WHERE isbn = '978-3-16-148410-0'), (SELECT id FROM authors WHERE firstname = 'Emma' AND lastname = 'Johnson'));