/**
 * Created by Alexandra Muresan on 4/5/2017.
 */
public class Main {

    public static void main(String[] args){

//        UserRepository userRepository = new UserRepository();
        //userRepository.addUser( new User(4,"raul","abc","raul","raul","muresan","abc"));
        //userRepository.deleteUser(3);


        //Participation participation = new Participation(new UserEditionEmb(),true);

        /*ist<User> list = userRepository.getAll();
        System.out.println("lista ID Useri");
        for(User user : list)
        {
            System.out.println(user.getId());
        }*/

        //User user = userRepository.findById(3);
        //System.out.println(user.getName() + " " + user.getSurname() + " " + user.getUsername());



        //Conference conference = new Con

        /*Configuration con = new Configuration().configure("/hibernate.cfg.xml").addAnnotatedClass(Conference.class).addAnnotatedClass(Edition.class).addAnnotatedClass(ConferenceSession.class).addAnnotatedClass(User.class).addAnnotatedClass(SessionChair.class).addAnnotatedClass(Participation.class).addAnnotatedClass(Paper.class).addAnnotatedClass(Review.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
        SessionFactory sf = con.buildSessionFactory(reg);
        User user = new User(1,"raul","abc","raul","raul","muresan","abc");
        Session session = sf.openSession();
        Transaction t = session.beginTransaction();
        session.save(user);
        t.commit();
        sf.close();
        System.out.println("Done");*/



    }
}
