INSERT INTO transaction_history
    (account_id, transaction_type, amount, source, status, reason_code, created_at)
VALUES

    (2, 'deposit', 10.00, 'online', 'success', 'Deposit transaction successful', '2023-01-30 13:15:11.224757'),
    (4, 'transfer', 45.00, 'online', 'success', 'Transfer transaction successful', '2023-02-11 13:15:17.998978'),
    (3, 'transfer', -45.00, 'online', 'success', 'Transfer transaction successful', '2023-02-11 13:15:17.995003'),
    (1, 'withdraw', -1.23, 'online', 'success', 'Withdrawal transaction successful', '2023-03-13 13:55:17.826398'),
    (2, 'withdraw', -5.00, 'online', 'success', 'Withdrawal transaction successful', '2023-03-29 13:55:29.154153'),
    (2, 'payment', 150.00, 'online', 'failed', 'Insufficient funds', '2023-04-09 13:57:04.881313'),
    (1, 'payment', -241.00, 'online', 'success', 'Payment transaction successful', '2023-04-09 13:57:54.914633'),
    (1, 'payment', -14.56, 'online', 'success', 'Payment transaction successful', '2023-04-09 13:58:46.638707');