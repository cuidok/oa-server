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