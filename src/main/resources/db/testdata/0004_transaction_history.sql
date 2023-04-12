INSERT INTO transaction_history
    (account_id, transaction_type, amount, currency, source, status, reason_code, created_at)
VALUES

    (2, 'deposit', 10.00, 'PLN', 'online', 'success', 'Deposit transaction successful', '2023-01-30 13:15:11.224757'),
    (4, 'payment', -50.00, 'PLN', 'online', 'success', 'Payment processed successful', '2023-02-01 18:52:15.806455'),
    (4, 'payment', -2500.00, 'PLN', 'online', 'success', 'Payment processed successfully', '2023-02-02 18:52:15.806455'),
    (4, 'payment', -123.00, 'PLN', 'online', 'success', 'Payment processed successful', '2023-02-04 18:52:15.806455'),
    (3, 'payment', -2742.40, 'PLN', 'online', 'success', 'Payment processed successful', '2023-02-05 18:52:15.806455'),
    (2, 'payment', -3009.43, 'PLN', 'online', 'success', 'Payment processed successful', '2023-02-06 18:52:15.806455'),
    (1, 'payment', -45809.27, 'PLN', 'online', 'success', 'Payment processed successful', '2023-02-07 18:52:15.806455'),
    (3, 'payment', -1234.38, 'PLN', 'online', 'success', 'Payment processed successful', '2023-02-08 18:52:15.806455'),
    (4, 'transfer', 45.00, 'PLN', 'online', 'success', 'Transfer transaction successful', '2023-02-11 13:15:17.998978'),
    (3, 'transfer', -45.00,  'PLN','online', 'success', 'Transfer transaction successful', '2023-02-11 13:15:17.995003'),
    (1, 'withdraw', -1.23, 'PLN', 'online', 'success', 'Withdrawal transaction successful', '2023-03-13 13:55:17.826398'),
    (2, 'withdraw', -5.00, 'PLN', 'online', 'success', 'Withdrawal transaction successful', '2023-03-29 13:55:29.154153'),
    (2, 'payment', 150.00, 'PLN', 'online', 'failed', 'Insufficient funds', '2023-04-09 13:57:04.881313'),
    (1, 'payment', -241.00, 'PLN', 'online', 'success', 'Payment transaction successful', '2023-04-09 13:57:54.914633'),
    (1, 'payment', -14.56, 'PLN', 'online', 'success', 'Payment transaction successful', '2023-04-09 13:58:46.638707');