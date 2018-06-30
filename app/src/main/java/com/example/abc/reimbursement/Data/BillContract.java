package com.example.abc.reimbursement.Data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class BillContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private BillContract() {
    }

    /**
     * The "Content authority" is a name for the entire content provider, similar to the
     * relationship between a domain name and its website.  A convenient string to use for the
     * content authority is the package name for the app, which is guaranteed to be unique on the
     * device.
     */
    public static final String CONTENT_AUTHORITY = "com.example.abc.reimbursement";

    /**
     * Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
     * the content provider.
     */
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    /**
     * Possible path (appended to base content URI for possible URI's)
     * For instance, content://com.example.android.pets/pets/ is a valid path for
     * looking at pet data. content://com.example.android.pets/staff/ will fail,
     * as the ContentProvider hasn't been given any information on what to do with "staff".
     */
    public static final String PATH_PETS = "";

    /**
     * Inner class that defines constant values for the pets database table.
     * Each entry in the table represents a single pet.
     */
    public static final class BillEntry implements BaseColumns {

        /**
         * The MIME type of the {@link #CONTENT_URI} for a list of pets.
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PETS;

        /**
         * The MIME type of the {@link #CONTENT_URI} for a single pet.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PETS;

        /**
         * The content URI to access the pet data in the provider
         */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PETS);

        /**
         * Name of database table for pets
         */
        public final static String TABLE_NAME = "Expenses";

        /**
         * Unique ID number for the Expense (only for use in the database table).
         * <p>
         * Type: INTEGER
         */
        public final static String COLUMN_EXPENSE_EXPENSE_ID = "Expense_ID";

        public final static String COLUMN_EXPENSE_BILL_ID = "Bill_ID";


        public final static String COLUMN_EXPENSE_NAME = "name";


        public final static String COLUMN_EXPENSE_STARTDATE = "start_date";


        public final static String COLUMN_EXPENSE_ENDDATE = "end_date";


        public final static String COLUMN_EXPENSE_BILLDATE = "bill_date";

        public final static String COLUMN_EXPENSE_RESTNAME = "restaurant_name";

        public final static String COLUMN_EXPENSE_CLIENTNAME = "client_name";

        public final static String COLUMN_EXPENSE_MEMBERS = "team_member";

        public final static String COLUMN_EXPENSE_PURPOSE = "purpose";

        public final static String COLUMN_EXPENSE_FINAL_AMOUNT = "final_amount";

        public final static String COLUMN_EXPENSE_CAT = "category";

        public final static String COLUMN_EXPENSE_SUBCAT = "sub_category";


    }
}