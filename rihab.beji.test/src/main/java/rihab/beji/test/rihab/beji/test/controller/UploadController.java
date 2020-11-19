package rihab.beji.test.rihab.beji.test.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import rihab.beji.test.rihab.beji.test.model.HRefModel;
import rihab.beji.test.rihab.beji.test.model.UserModel;
import rihab.beji.test.rihab.beji.test.util.Constants;

@Controller
public class UploadController {

	Map<String, HRefModel> uris = new HashMap<>();

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView upload() {
		return new ModelAndView("upload");
	}

//	@RequestMapping(value = "/files/list", method = RequestMethod.GET)
//	public String listFiles(Model model) {
//		List<Path> lodf = new ArrayList<>();
//		List<HRefModel> uris = new ArrayList<>();
//		
//		try {
//			lodf = storageService.listSourceFiles(storageService.getUploadLocation());
//			for(Path pt : lodf) {
//				HRefModel href = new HRefModel();
//				href.setHref(MvcUriComponentsBuilder
//						.fromMethodName(UploadController.class, "serveFile", pt.getFileName().toString())
//						.build()
//						.toString());
//				
//				href.setHrefText(pt.getFileName().toString());
//				uris.add(href);
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		model.addAttribute("listOfEntries", uris);
//		return "file_list :: urlFileList";
//	}

	@RequestMapping(value = "/files/list", method = RequestMethod.GET)
	public String listFiless(Model model) {

		List<UserModel> allUsersDetails;
		UsersController usersController;
		usersController = new UsersController();
		allUsersDetails = new ArrayList();

		String path = Constants.PATH_TO_TWITTER_FILE;
		String path2 = Constants.PATH_TO_USER_FILE;

		if (uris.size() != 0 && uris.containsKey("fl")) {
			path = uris.get("fl").getHrefText();
		}
		if (uris.size() != 0 && uris.containsKey("ur")) {
			path2 = uris.get("ur").getHrefText();
		}

		System.err.println(path);

		usersController.returnFormattedUsersDetailFromFile(path, Constants.DELIMETER_FOLLOWERS, allUsersDetails);

		usersController.returnFormattedUsersDetailFromFile(path2, Constants.DELIMETER_USERS, allUsersDetails);

		model.addAttribute("listOfEntries", allUsersDetails);
		return "file_list :: urlFileList";
	}

	@RequestMapping(value = "/files/uploadur", method = RequestMethod.POST)
	public String handleFileUploadUser(@RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) {

		Path filepath = Paths.get(Constants.UPLOAD_LOCATION, file.getOriginalFilename());
		try (OutputStream os = Files.newOutputStream(filepath)) {
			try {
				os.write(file.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HRefModel href = new HRefModel();

		href.setHrefText(Constants.UPLOAD_LOCATION + file.getOriginalFilename());
		uris.put("ur", href);

		return "redirect:/";
	}

	@RequestMapping(value = "/files/upload", method = RequestMethod.POST)
	public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

		Path filepath = Paths.get(Constants.UPLOAD_LOCATION, file.getOriginalFilename());
		try (OutputStream os = Files.newOutputStream(filepath)) {
			try {
				os.write(file.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HRefModel href = new HRefModel();

		href.setHrefText(Constants.UPLOAD_LOCATION + file.getOriginalFilename());
		uris.put("fl", href);

		return "redirect:/";
	}
}
