package com.helpfulproduction.mywords

class DatabaseFillException(cause: Exception): Exception("Error while filling database", cause)