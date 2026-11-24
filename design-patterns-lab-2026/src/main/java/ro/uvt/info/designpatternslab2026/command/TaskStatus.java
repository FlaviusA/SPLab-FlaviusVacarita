package ro.uvt.info.designpatternslab2026.command;

public record TaskStatus(String id, String status, Object result, String error) {
}
