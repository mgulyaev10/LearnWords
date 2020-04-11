package com.helpfulproduction.mywords.database

class DatabaseFillException(cause: Exception): Exception("Error while filling database", cause)