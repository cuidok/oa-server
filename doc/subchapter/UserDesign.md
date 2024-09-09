# User design document

## Generate user token

The system use JWT technology to generate user token, the function follows the steps below:

1. Accept the user id.
2. Check input parameters.
3. Use the user id to generate the token.
4. Return the token.

The valid time of the token is 7 days.