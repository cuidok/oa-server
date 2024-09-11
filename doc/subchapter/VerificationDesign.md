# Verification design document

## Login verification design

### Login verification container

The login verification container provides the function to store, query and delete the login verification.

The verification container uses the `login_verification_key` as the key, and the login verification code as the value.

### Generate login verification key

The system generates the login verification key to user for generating and querying the login verification code.

The design of login verification key is as follows:

1. The login verification key must be unique.

The generating steps of the login verification key are as follows:

1. Get the current time.
2. Generate a random number from 10000 to 99999.
3. Combine the current time and the random number to generate the login verification key.
4. Use the AES algorithm to encrypt the login verification key.
5. Return the login verification key.

The login verification key is valid for 5 minutes.

Pay attention to query the login verification code, you need to use AES algorithm to decrypt the login verification key
which is got from the user.

#### ConcurrentHashMap implementation

The login verification container can be implemented by using the `ConcurrentHashMap` class.

##### Store login verification

The function stores the login verification, the function follows the steps below:

1. Accept the login verification key and code.
2. Check input parameters.
3. Store the login verification.
4. Return the login verification code.

##### Delete login verification

The function deletes the login verification, the function follows the steps below:

1. Accept the login verification key.
2. Check input parameters.
3. Delete the login verification.

##### Query login verification

The function queries the login verification, the function follows the steps below:

1. Accept the login verification key.
2. Check input parameters.
3. Query the login verification.
4. Check the expired time of the login verification.
5. Return the login verification code.

If the login verification does not exist, the function will return `Optional.empty()`.

If the login verification is expired, the function will remove it and return `Optional.empty()`.
