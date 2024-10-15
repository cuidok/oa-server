# Software design document

This document describe the software design of the project. Those subchapters are described in more detail in the corresponding document.

If you want to know more about the database design of the project, please refer to the [Database design](./DatabaseDesign.md).

Pay attention to the project is build with AI, so we keep the prompt in AI part.

## Web exception handling method

If necessary, we should log the exception message at the point where it occurs, and create the error response body for 
client at the WebExceptionHandler.

The error response body for each Exception is described in the [WebDesign.md](./subchapter/WebDesign.md).