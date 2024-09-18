# User design document

## Generate user token

The system uses JWT technology to generate user token, the function follows the steps below:

1. Accept the user id.
2. Check input parameters.
3. Use the user id to generate the token.
4. Return the token.

The valid time of the token is 7 days.

## Parse user token

The system uses JWT technology to parse user token, the function follows the steps below:

1. Accept the token.
2. Check input parameters.
3. Parse the token.
4. Return the `UserDetail` object.

The system will check the validity time of the token, use the sign key to check the token.

## User token container

The user token container provides the function to store, query and delete the user token.

### ConcurrentHashMap implementation

The user token container can be implemented by using the `ConcurrentHashMap` class.

#### Store user token

The function stores the user token, the function follows the steps below:

1. Accept the user id and token.
2. Check input parameters.
3. Store the user token.

If the user token exists, the function will update the UserToken object corresponding to the token.

### Delete user token

The function deletes the user token, the function follows the steps below:

1. Accept the token.
2. Check input parameters.
3. Delete the user token.

#### Query user token

The function queries the user token, the function follows the steps below:

1. Accept the token.
2. Check input parameters.
3. Query the user token.
4. Return the UserToken object.

If the user token does not exist, the function will return `Optional.empty()`.

If the user token is expired, the function will return `Optional.empty()`.

## Register

The system provides the function to register a new user.

The registering steps of the user are as follows:

1. Accept the register parameters.
2. Check input parameters.
3. Check the password whether meets the requirements.
4. Check the email whether meets the requirements.
5. Check the username whether exists.
6. Check the email whether exists.
7. User spring-security-crypto to encrypt the password.
8. Save the user information into the user table.

The email must follow the email format.

The password must be at least 8 characters long and contain at least one letter and one number.

## Login

The system provides the function to login.

The login steps of the user are as follows:

1. Accept the login parameters.
2. Check input parameters.
3. Query the user information by username
4. Check the login verification code.
5. Check the username and password.
6. Generate the user token.
7. Save the user token into the user token container.
8. Return the user token.