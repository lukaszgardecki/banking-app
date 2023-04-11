INSERT INTO payments
    (account_from_id, transact_id, beneficiary, beneficiary_acc_no, amount, currency, reference, status, reason_code, created_at)
VALUES
    (4, 2, 'Święty Mikołaj', '89123412340000000078965432', 50.00, 'PLN', 'Na lepszy worek', 'success', 'Payment processed successfully', '2023-02-01 18:52:15.806455'),
    (4, 3, 'Malanowski i Partnerzy', '89123412340000000078965432', 2500.00, 'PLN', 'Sprawa', 'success', 'Payment processed successfully', '2023-02-02 18:52:15.806455'),
    (4, 4, 'Kamil Kowalski', '89123412340000000078965432', 123.00, 'PLN', 'Ona czuje we mnie piniondz', 'success', 'Payment processed successfully', '2023-02-04 18:52:15.806455'),
    (3, 5, 'Ania Dobra', '89123412340000000078965432', 2742.40, 'PLN', 'Panie dobry jak chleb', 'success', 'Payment processed successfully', '2023-02-05 18:52:15.806455'),
    (2, 6, 'Teresa Zwanaświętom', '89123412340000000078965432', 3009.43, 'PLN', 'Na złote lizaki', 'success', 'Payment processed successfully', '2023-02-06 18:52:15.806455'),
    (1, 7, 'Mojżesz Tenodgóry', '89123412340000000078965432', 45809.27, 'PLN', 'Sprzedałem krzak z góry', 'success', 'Payment processed successfully', '2023-02-07 18:52:15.806455'),
    (3, 8, 'Pani Krysia', '89123412340000000078965432', 1234.38, 'PLN', 'Dwa szoty poproszę', 'success', 'Payment processed successfully', '2023-02-08 18:52:15.806455'),
    (2, 13, 'Jan Kowalski', '11222233330000000000001234', 150.00, 'PLN', 'Opłata za czyste powietrze', 'failed', 'Insufficient funds', '2023-04-09 13:57:04.881313'),
    (1, 14, 'Łukasz Tralala', '44999999990000000000002333', 241.00, 'PLN', 'Opłata za wszystko', 'success', 'Payment transaction successful', '2023-04-09 13:57:54.914633'),
    (1, 15, 'Gazownia w Katowicach', '00000000000000000000000054', 14.56, 'PLN', 'Opłata za gaz', 'success', 'Payment transaction successful', '2023-04-09 13:58:46.638707');