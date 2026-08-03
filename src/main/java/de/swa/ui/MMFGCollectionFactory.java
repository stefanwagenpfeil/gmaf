package de.swa.ui;

import java.util.Hashtable;

/**
 * Created by Patrick Steinert on 27.12.23.
 */
public class MMFGCollectionFactory {

	private static MMFGCollection instance;
	private static Hashtable<String, MMFGCollection> sessions = new Hashtable<String, MMFGCollection>();


	public static synchronized MMFGCollection createOrGetCollection() {
		if (instance == null) {
			String cm = Configuration.getInstance().getCollectionManager();

			// Create Class via Reflection
			try {
				instance = ((MMFGCollection) Class.forName(cm).newInstance());
				//instance.getInstance();
				instance.init();

			} catch (InstantiationException e) {
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
		}
		return instance;


	}

	public static synchronized MMFGCollection createOrGetCollection(String session_id) {
		if (sessions.get(session_id) != null)
			return sessions.get(session_id);
		else {
//			MMFGCollection coll = new MMFGCollection();
//			coll.init();
//			sessions.put(session_id, coll);
//			return coll;

			String cm = Configuration.getInstance().getCollectionManager();

			// Create Class via Reflection
			//try {
				//MMFGCollection aInstance = ((MMFGCollection) Class.forName(cm).newInstance());
				MMFGCollection aInstance = createOrGetCollection();
				//instance.getInstance();
				sessions.put(session_id, aInstance);
				//aInstance.init();
				return aInstance;

			//} catch (InstantiationException e) {
			//	throw new RuntimeException(e);
			//} catch (IllegalAccessException e) {
			//	throw new RuntimeException(e);
			//} catch (ClassNotFoundException e) {
			//	throw new RuntimeException(e);
			//}
		}
	}
}
