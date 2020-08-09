Mouse controller with endpoint:

    @RestController
    public class MouseController {
        @Inject
        private MouseRepository mouseRepository;
    
        @RequestMapping(value="/mouses", method=RequestMethod.GET)
        public ResponseEntity<Iterable<Mouse>> getAllMouses() {
            return new ResponseEntity<>(mouseRepository.findAll(), HttpStatus.OK);
        }
        
        @RequestMapping(value="/mouses/{mouseId}", method=RequestMethod.PUT)
        public ResponseEntity<?> renameMouse(@Valid @ModelAttribute("mouse") Name name, @PathVariable long pollId) {
            Mouse mouse = mouseRepository.findById(pollId).get();
            mouse = mouseRepository.save(mouse);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

Repository:

    public interface MouseRepository extends CrudRepository<Mouse, Long> {}