# Web design document

This document describes the web controller, controller advice, basic model and so on.

## Controller advice

### Handle Illegal argument exception

This function's main task is to handle the `IllegalArgumentException`, the function follows the steps below:

1. Use log level to log the error message.
2. Return the `400` error code to the client.

### Handle illegal token exception

This function's main task is to handle the `IllegalTokenException`, the function follows the steps below:

1. Use log level to log the error message.
2. Return the `400` error code and user message to the client.