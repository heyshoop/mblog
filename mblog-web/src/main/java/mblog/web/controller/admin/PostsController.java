/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package mblog.web.controller.admin;

import java.util.List;

import mblog.scheduler.entity.Token;
import mtons.modules.lang.Const;
import mtons.modules.pojos.Data;
import mtons.modules.pojos.Paging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import mblog.base.lang.Consts;
import mblog.core.biz.PostBiz;
import mblog.core.data.Post;
import mblog.core.persist.service.PostService;
import mblog.web.controller.BaseController;

import javax.servlet.http.HttpServletRequest;
	/**
	 * @Author 阁楼麻雀
	 * @Date 2016-5-18 13:50
	 * @Desc
	 */
@Controller("mng_post_ctl")
@RequestMapping("/admin/posts")
public class PostsController extends BaseController {
	@Autowired
	private PostService postService;
	@Autowired
	private PostBiz postBiz;
	
	@RequestMapping("/list")
	public String list(Integer pn, String title, ModelMap model, HttpServletRequest request) {
		long id = ServletRequestUtils.getLongParameter(request, "id", Const.ZERO);
		int group = ServletRequestUtils.getIntParameter(request, "group", Const.ZERO);

		Paging page = wrapPage(pn);

		postService.paging4Admin(page, id, title, group);
		model.put("page", page);
		model.put("title", title);
		model.put("id", id);
		model.put("group", group);
		return "/admin/posts/list";
	}
	
	/**
	 * 跳转到文章编辑方法
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String toUpdate(Long id, ModelMap model) {
		Post ret = postService.get(id);
		model.put("view", ret);
		return "/admin/posts/update";
	}
	
	/**
	 * 更新文章方法
	 * @author LBB
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String subUpdate(Post p) {
		if (p != null) {
			postService.update(p);
		}
		return "redirect:/admin/posts/list";
	}

	@RequestMapping("/featured")
	public @ResponseBody Data featured(Long id, HttpServletRequest request) {
		Data data = Data.failure("操作失败");
		int featured = ServletRequestUtils.getIntParameter(request, "featured", Consts.FEATURED_ACTIVE);
		if (id != null) {
			try {
				postBiz.updateFeatured(id, featured);
				data = Data.success("设置成功", Data.NOOP);
			} catch (Exception e) {
				data = Data.failure(e.getMessage());
			}
		}
		return data;
	}
	
	@RequestMapping("/delete")
	public @ResponseBody Data delete(@RequestParam("id") List<Long> id) {
		Data data = Data.failure("操作失败");
		if (id != null) {
			try {
				postBiz.delete(id);
				data = Data.success("删除成功", Data.NOOP);
			} catch (Exception e) {
				data = Data.failure(e.getMessage());
			}
		}
		return data;
	}
	/**
	 * @Author 阁楼麻雀
	 * @Date 2016-5-18 11:51
	 * @Desc 文章推送方法
	 */

	@RequestMapping("/seed")
	public @ResponseBody Data seed(@RequestParam("id") List<Long> id) {
		Data data = Data.failure("操作失败");
		if (id != null) {
			try {

				data = Data.success("操作成功", Data.NOOP);
			} catch (Exception e) {
				data = Data.failure(e.getMessage());
			}
		}
		System.out.println("accesstoken的内存地址为------------------------------------"+Token.getInstance());
		System.out.println("内存中的accesstoken为--------------------------------------"+Token.getInstance().getToken());
		return data;
	}
}
