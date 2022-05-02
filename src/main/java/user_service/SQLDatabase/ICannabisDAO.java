package user_service.SQLDatabase;

import user_service.model.Cannabis;


import java.sql.SQLException;

public interface ICannabisDAO {



    Cannabis getCannabis(String CannabisName) throws DALException, SQLException;






    // ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
    // Exceptions

    class DALException extends Exception {
        public DALException(String msg){ super(msg); }
    }


/*removed so far, cause it caused an error
        public default String getCannabis() {

            return CannabisName;
        }

*/
    class CannabisExistsException extends ICannabisDAO.DALException {
        private final String CannabisName;

        public CannabisExistsException(String CannabisName){
            super("Unknown Cannabis Name '" + CannabisName + "'");
            this.CannabisName = CannabisName;
        }

        public String getCannabisName() {
            return CannabisName;
        }
    }


    class UnknownCannabisException extends ICannabisDAO.DALException {
        private final String Cannabis;

        public UnknownCannabisException(String Cannabis){
            super("Unknown Cannabis '" + Cannabis + "'");
            this.Cannabis = Cannabis;
        }

        public String getCannabisName() {
            return Cannabis;
        }
    }

 }

