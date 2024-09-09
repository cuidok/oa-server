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