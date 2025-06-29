package org.milan.ai.model;

import java.util.List;

/**
 * @author Milan Rathod
 */
public record GeminiModelListResponse(String object, List<GeminiModel> data) {
}