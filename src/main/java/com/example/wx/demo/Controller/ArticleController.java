package com.example.wx.demo.Controller;

import com.example.wx.demo.Entity.ArticleEntity;
import com.example.wx.demo.Models.ArticleInfo;
import com.example.wx.demo.Services.ArticleServices;
import com.example.wx.demo.Utils.PageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(description = "文章信息接口")
@RequestMapping(value = "/api/v1")
@CrossOrigin
public class ArticleController {
    @Autowired
    private ArticleServices articleServices;


    @PostMapping(value = "/saveArticleInfo")
    @ApiOperation(value = "保存文章信息", notes = "saveArticleInfoMessage", produces = "application/json;charset=UTF-8")
    public ResponseEntity<ArticleEntity> saveArticleMessageInfo(@RequestBody ArticleInfo articleInfo) {
        ArticleEntity result = articleServices.saveArticleMessage(articleInfo);
        HttpStatus status = result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<ArticleEntity>(result, status);
    }

    @DeleteMapping(value = "/deleteArticle/blok/{id}/person/{personId}")
    @ApiOperation(value = "删除自己发布的文章", notes = "deleteArticle", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Boolean> deleteArticleOfOwn(@RequestParam String id, @RequestParam String personId) {
        Boolean result = articleServices.deleteArticleOfOwn(id, personId);
        HttpStatus status = result ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<Boolean>(result, status);
    }

    @GetMapping("/articleInfo/{page}/{size}")
    @ApiOperation(value = "文章分页获取", notes = "getArticleInfoByPageAndSize", produces = "application/json;charset=UTF-8")
    public ResponseEntity<PageBean<ArticleEntity>> getUerInfoByPageSize(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                        @RequestParam(value = "size", defaultValue = "5") int size) {
        PageBean<ArticleEntity> result = articleServices.getArticleByPageAndSize(page,size);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/myArticleInfo/{id}/{page}/{size}")
    @ApiOperation(value = "根据用户的id获取文章分页获取", notes = "getArticleInfoByPersonalIdPageAndSize", produces = "application/json;charset=UTF-8")
    public ResponseEntity<PageBean<ArticleEntity>> getUerInfoByCurrentIdPageSize(
            @RequestParam String id,
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "5", required = false) Integer size
    ) {
        PageBean<ArticleEntity> result = articleServices.getOwnArticleByPageAndSize(id, page,size);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/updateLooks/{id}")
    @ApiOperation(value = "更新文章的阅读量", notes = "updateArticleLooks", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Boolean> updateArticleOfLooks(@RequestParam String id) {
        Boolean  result = articleServices.UpdateArticleLooksById(id);
        HttpStatus status = result ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<Boolean>(result, status);
    }
    @PutMapping("/updateStars/{id}")
    @ApiOperation(value = "更新文章的点赞量", notes = "updateArticleStars", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Boolean> updateArticleOfStars(@RequestParam String id) {
        Boolean  result = articleServices.UpdateArticleStars(id);
        HttpStatus status = result ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<Boolean>(result, status);
    }

    @GetMapping("/getArticle/{typeName}")
    @ApiOperation(value = "根据类型的id获取文章分页获取", notes = "getArticleInfoByTypePageAndSize", produces = "application/json;charset=UTF-8")
    public ResponseEntity<PageBean<ArticleEntity>> getUerInfoByTypePageSize(
            @PathVariable("typeName") String typeName,
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "5", required = false) Integer size
    ) {
        PageBean<ArticleEntity> result = articleServices.getArticleByType(typeName, page,size);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
