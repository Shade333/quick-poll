Mouse controller with endpoint:

    @RestController
    public class MouseController {
        @Inject
        private MouseRepository mouseRepository;
    
        @RequestMapping(value="/mouses", method=RequestMethod.GET)
        public ResponseEntity<Iterable<Mouse>> getAllMouses() {
            return new ResponseEntity<>(mouseRepository.findAll(), HttpStatus.OK);
        }
    }

Repository:

    public interface MouseRepository extends CrudRepository<Mouse, Long> {}